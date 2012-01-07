package com.fasterxml.util.membuf.impl;

import com.fasterxml.util.membuf.SegmentAllocator;
import com.fasterxml.util.membuf.base.BytesSegment;
import com.fasterxml.util.membuf.base.BytesSegmentAllocator;

/**
 * {@link SegmentAllocator} implementation that allocates
 * {@link BytesArraySegment}s, which are simple byte array backed segments.
 */
public class BytesArraySegmentAllocator extends BytesSegmentAllocator
{
    /*
    /**********************************************************************
    /* Life-cycle
    /**********************************************************************
     */
    
    public BytesArraySegmentAllocator(int segmentSize, int minSegmentsToRetain, int maxSegments)
           
    {
        super(segmentSize, minSegmentsToRetain, maxSegments);
    }
    
    /*
    /**********************************************************************
    /* Abstract method implementations
    /**********************************************************************
     */
    
    protected BytesSegment _allocateSegment()
    {
        // can reuse a segment returned earlier?
        if (_reusableSegmentCount > 0) {
            BytesSegment segment = _firstReusableSegment;
            _firstReusableSegment = segment.getNext();
            ++_bufferOwnedSegmentCount; 
            --_reusableSegmentCount;
            return segment;
        }
        BytesSegment segment = new BytesArraySegment(_segmentSize);
        ++_bufferOwnedSegmentCount; 
        return segment;
    }
}
